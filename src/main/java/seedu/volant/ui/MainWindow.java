package seedu.volant.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.LogicManager;
import seedu.volant.commons.logic.Page;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.trippage.logic.TrippageLogicManager;
import seedu.volant.ui.pages.homepage.TripListPanel;
import seedu.volant.ui.pages.trippage.TripPage;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Page currentPage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private UiPart<Region> mainPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane mainPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        LogicManager t = ((LogicManager) logic);
        mainPanel = new TripListPanel(t.getFilteredTripList());
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getVolantFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public void updateLogic(Logic logic) {
        this.logic = logic;
    }


    /**
     * Handles command to go to a directory
     * TODO: Fix this javadoc
     */
    @FXML
    public void handleGoto(Page page) {
        switch (page) {
        /*
            case JOURNAL:
                updateLogic();
                setPagePlaceholder();
            case HOMEPAGE:
                updateLogic();
                setPagePlaceholder();
            case ITINERARY:
                updateLogic();
                setPagePlaceholder();
        */
        default:
            break;
        }
    }

    /**
     * Handles command to go to a trip in the trip list.
     * @param trip to navigate to.
     */
    @FXML
    public void handleGotoTrip(Trip trip) {
        updateLogic(new TrippageLogicManager());
        mainPanelPlaceholder.getChildren().removeAll(mainPanel.getRoot());
        mainPanel = new TripPage(trip);
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
    }

    /**
     * Handles command to go back one directory.
     */
    @FXML void handleBack() {

    }

    /**
     * Executes the command and returns the result.
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isGoto()) {
                switch (commandResult.getPage()) {
                case TRIP:
                    handleGotoTrip(commandResult.getTrip());
                default:
                    handleGoto(commandResult.getPage());
                }
            }

            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

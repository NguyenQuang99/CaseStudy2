package bookmanage;

import filemanager.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class bookManagerController implements Initializable {
    @FXML
    TableView<Books> tableView;
    @FXML
    TableColumn<Books, String> idColumn;
    @FXML
    TableColumn<Books, String> bookNameColumn;
    @FXML
    TableColumn<Books, String> authorColumn;
    @FXML
    TableColumn<Books, String> categoryColumn;
    @FXML
    TableColumn<Books, String> manufacColumn;
    @FXML
    TableColumn<Books, Integer> yearColumn;

    @FXML
    TextField txtCodeBook;
    @FXML
    TextField txtAuthor;
    @FXML
    TextField txtManufac;
    @FXML
    TextField txtName;
    @FXML
    TextField txtCategory;
    @FXML
    TextField txtYear;
    @FXML
    TextField txtSearch;

    List<Books> listBooks = new ArrayList<>();
    @FXML
    Button btnSave;
    @FXML
    Button btnEdit;
    @FXML
    ComboBox<String> comboboxSearch;
    String[] comboxList = {"Mã Sách", "Tên Sách", "Tác Giả", "Thể Loại", "Nhà Xuất Bản ", "Năm Xuất Bản"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("codeBook"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("nameBook"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("category"));
        manufacColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("publisher"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        btnEdit.setDisable(true);
        tableView.getItems().addAll(listBooks);
        comboboxSearch.getItems().addAll(comboxList);
        txtSearch.textProperty().addListener((o, oldValue, newValue) -> {
            searchCodeBook(txtSearch.getText());
       });
        txtSearch.textProperty().addListener((o, oldValue, newValue) -> {
           searchNameBook(txtSearch.getText());
        });
        txtSearch.textProperty().addListener((o, oldValue, newValue) -> {
            searchyear(Integer.parseInt(txtSearch.getText()));
        });
        txtSearch.textProperty().addListener((o, oldValue, newValue) -> {
            searchmanufac(txtSearch.getText());
        });
        txtSearch.textProperty().addListener((o, oldValue, newValue) -> {
            searchCategory(txtSearch.getText());
        });
        txtSearch.textProperty().addListener((o, oldValue, newValue) -> {
            searchAuthor(txtSearch.getText());
        });
    }

    public void loadBooklist() {
        tableView.getItems().clear();
        for (Books book : listBooks) {
            tableView.getItems().add(book);
        }
    }

    //them
    public void add(ActionEvent e) throws Exception {
        Books books = new Books();
        books.setNameBook(txtName.getText());
        books.setCodeBook(txtCodeBook.getText());
        books.setAuthor(txtAuthor.getText());
        books.setCategory(txtCategory.getText());
        books.setPublisher(txtManufac.getText());
        books.setYear(Integer.parseInt(txtYear.getText()));
        for (Books book : listBooks) {
            if (book.getCodeBook().equals(txtCodeBook.getText()) || txtCodeBook.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System information");
                alert.setContentText("The book id was exist . try again!");
                alert.showAndWait();
                return;
            }
        }
        listBooks.add(books);
        loadBooklist();
        writeFile();
        clear();

    }


    public void searchCodeBook(String codeBook) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
//            if (codeBook.toLowerCase().equals(books.getCodeBook().toLowerCase())) {
//                tableView.getItems().add(books);
//            }
            if((books.getCodeBook().toLowerCase().contains(codeBook.toLowerCase()))) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchNameBook(String nameBook) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if ((books.getNameBook().toLowerCase().contains(nameBook.toLowerCase()))) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchAuthor(String author) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if ((books.getAuthor().toLowerCase().contains(author.toLowerCase()))) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchCategory(String category) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if ((books.getCategory().toLowerCase().contains(category.toLowerCase()))) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchmanufac(String manufac) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if ((books.getNameBook().toLowerCase().contains(manufac.toLowerCase()))) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchyear(int year) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (year == books.getYear()) {
                tableView.getItems().add(books);
            }
        }
    }


    //ghi file
    public void writeFile() throws Exception {
        FileManager<Books> fileManager = new FileManager<>();
        fileManager.writeFile("src/bookmanage/books.txt", listBooks);
    }

    //đọc file
    public void readFile() throws Exception {
        FileManager<Books> fileManager = new FileManager<>();
        listBooks.clear();
        listBooks.addAll(fileManager.readFile("src/bookmanage/books.txt"));
    }

    // tìm kiếm
    public void Search() {
        String search = txtSearch.getText();
        int selectedIndex = comboboxSearch.getSelectionModel().getSelectedIndex();
        if (search.equals("")) {
            loadBooklist();
        } else {
            switch (selectedIndex) {
                case 0:
                    searchCodeBook(txtSearch.getText());
                    break;
                case 1:
                    searchNameBook(txtSearch.getText());
                    break;
                case 2:
                    searchAuthor(txtSearch.getText());
                    break;
                case 3:
                    searchCategory(txtSearch.getText());
                    break;
                case 4:
                    searchmanufac(txtSearch.getText());
                    break;
                case 5:
                    searchyear(Integer.parseInt(txtSearch.getText()));
                    break;
            }
        }
    }

    //xoa
    public void delete(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get().getButtonData() == ButtonBar.ButtonData.YES) {

            Books books = tableView.getSelectionModel().getSelectedItem();
        listBooks.remove(books);
        loadBooklist();
        }


    }

    public void TableSelectionChanged() {
        Books book = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(book == null);
    }

    //sua
    public void edit(ActionEvent e) {
        Books book = tableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            btnEdit.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("No book selected. Please try again.");
            alert.showAndWait();
        } else {
            txtCodeBook.setText(book.getCodeBook());
            txtName.setText(book.getNameBook());
            txtAuthor.setText(book.getAuthor());
            txtCategory.setText(book.getCategory());
            txtManufac.setText(book.getPublisher());
            txtYear.setText(String.valueOf(book.getYear()));
     //       txtCodeBook.setDisable(true);
            btnSave.setDisable(false);
            btnEdit.setDisable(true);

        }
    }

    //luu
    public void save(ActionEvent e) throws Exception {
        Books book = tableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            btnSave.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("No book selected. Please try again.");
            alert.showAndWait();
        } else {
            book.setNameBook(txtName.getText());
            book.setAuthor(txtAuthor.getText());
            book.setCategory(txtCategory.getText());
            book.setPublisher(txtManufac.getText());
            book.setYear(Integer.parseInt(txtYear.getText()));
            btnSave.setDisable(true);
     //       txtCodeBook.setDisable(false);
            btnEdit.setDisable(false);

      //      listBooks.add(book);
            loadBooklist();
            writeFile();
            clear();

            save();

        }
    }

    public void clear() throws Exception {
        txtCodeBook.clear();
        txtName.clear();
        txtAuthor.clear();
        txtManufac.clear();
        txtYear.clear();
        txtCategory.clear();
    }

    public void save() throws Exception {
        try {
            FileOutputStream fos = new FileOutputStream("src/bookmanage/books.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Books books : listBooks ) {
                oos.writeObject(books);
            }
//            oos.writeObject(productList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

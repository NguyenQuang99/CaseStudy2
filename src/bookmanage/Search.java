package bookmanage;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class Search {
    List<Books> listBooks = new ArrayList<>();
    @FXML
    TableView<Books> tableView;

    public void searchCodeBook(String nameBook) {
        String input = nameBook.toLowerCase();
        boolean check = false;
        String string;
        int i;
        for (i = 0; i < listBooks.size(); i++) {
            string = (listBooks.get(i).getNameBook()).toLowerCase();
            for (int j = 0; j < (listBooks.get(i).getNameBook()).length(); j++) {
                if (listBooks.get(j).getNameBook().contains(input)) {
                    check = true;
                    break;
                }
                if (check)
                    tableView.getItems().add(listBooks.get(i));
            }

        }

    }
}

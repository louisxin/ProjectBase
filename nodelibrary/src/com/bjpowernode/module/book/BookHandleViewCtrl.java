package com.bjpowernode.module.book;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.global.util.Alerts;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BookHandleViewCtrl {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField bookNameField;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField publisherField;

    @FXML
    private ComboBox typeField;

    private Stage stage;

    private TableView<Book> bookTableView;

    private ObservableList<Book> books;

    //�޸ĵ�book����
    private Book book;

    /*
        ��ӻ��޸�����
     */
    @FXML
    private void addOrEditBook() {
        try {
            String id = bookIdField.getText();
            if ("".equals(id) || null == id) {
                //��Ӳ���
                Book book = new Book();
                populate(book);
                book.setStatus(Constant.STATUS_STORAGE);
                books.add(book);
            }else {
                //�޸Ĳ���
                populate(this.book);
                //ˢ��
                bookTableView.refresh();
            }

            stage.close();
            Alerts.success("�ɹ�", "�����ɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.error("ʧ��","����ʧ��");
        }

    }

    private void populate(Book book) {
        book.setBookName(bookNameField.getText());
        book.setAuthor(authorField.getText());
        book.setIsbn(isbnField.getText());
        book.setPublisher(publisherField.getText());
        book.setType(typeField.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void closeView() {
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void setBooks(ObservableList<Book> books) {
        this.books = books;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        if (book != null) {
            //����޸Ľ����ֵ
            bookNameField.setText(book.getBookName());
            authorField.setText(book.getAuthor());
            isbnField.setText(book.getIsbn());
            bookIdField.setText(String.valueOf(book.getId()));
            publisherField.setText(book.getPublisher());
            typeField.setValue(book.getType());
        }

    }

    public TableView<Book> getBookTableView() {
        return bookTableView;
    }

    public void setBookTableView(TableView<Book> bookTableView) {
        this.bookTableView = bookTableView;
    }
}

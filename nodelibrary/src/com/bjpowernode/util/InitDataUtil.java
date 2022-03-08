package com.bjpowernode.util;

import com.bjpowernode.bean.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InitDataUtil {
    public static void main(String[] args) {
        //��ʼ���û�����
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001, "�Ŵ�", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH,userList);

        //��ʼ��ͼ������
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));
        bookList.add(new Book(2,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH,bookList);

        //��ʼ����������
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001, "�Ŵ�", Constant.USER_OK, BigDecimal.TEN);
        Book book = new Book(1, "java����", "����", Constant.TYPE_COMPUTER, "123-1", "��е��ҵ������", Constant.STATUS_STORAGE);

        Lend lend = new Lend();

        //ʹ��UUID���ɱ��
        lend.setId(1);
        lend.setUser(user);
        lend.setBook(book);
        lend.setStatus(Constant.STATUS_LEND);
        LocalDate begin = LocalDate.now();
        lend.setLendDate(begin);
        //���ù黹����
        lend.setReturnDate(begin.plusDays(30));

        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);
    }

    /**
     * ��ʼ������
     */
    public static void initData(String path,List<?> list) {
        ObjectOutputStream oos = null;
        //��������ļ���
        try {
            File directory = new File(path.split("/")[0] + "/");
            File file = new File(path);
            //�ж��ļ����Ƿ����
            if (!directory.exists()) {
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if (!file.exists()) {
                file.createNewFile();
                //���ö����������list����д�����ļ���
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر���
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

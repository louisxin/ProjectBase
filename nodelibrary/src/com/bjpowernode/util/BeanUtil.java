package com.bjpowernode.util;

import com.bjpowernode.bean.User;

import java.lang.reflect.Field;

public class BeanUtil {

    /**
     *  ��������ֵ�Ŀ���
     * @param origin
     * @param dest
     */
    public static void populate(Object origin, Object dest) {
        try {
            //ʹ�÷������������
            //�ж����������Ƿ���ͬһ����
            if (origin.getClass() != dest.getClass()) {
                throw new RuntimeException("��������������ͬһ����");
            }

            Class<?> clazz = origin.getClass();
            //��ȡorigin�е�����
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                //�ų�serialVersionUID
                if ("serialVersionUID".equals(f.getName())) {
                    continue;
                }
                //���Ʒ�װ
                f.setAccessible(true);
                //��dest�������ҵ���Ӧ������ֵ��Ȼ��ֵ��origin��Ӧ��������
                f.set(origin,f.get(dest));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

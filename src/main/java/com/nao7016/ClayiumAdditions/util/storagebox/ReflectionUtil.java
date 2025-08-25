package com.nao7016.ClayiumAdditions.util.storagebox;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReflectionUtil {

    /**
     * 指定クラスのフィールドをすべて得る
     * @param insClass フィールドを取り出す対象のクラス。
     * @param instance フィールドを取り出す対象のクラスのインスタンス。static フィールドを得る場合は null にする。
     * @param targetClass 取り出したいフィールドの型。一致するフィールドがすべて取り出される。
     * @return 取り出したフィールド
     */
    public static List<Field> findFields(Class<?> insClass, Object instance, Class<?> targetClass) {
        List<Field> list = new ArrayList<Field>();
        if (null == insClass && null != instance) {
            insClass = instance.getClass();
        }
        Field[] Fields = Objects.requireNonNull(insClass).getDeclaredFields();
        for (Field field : Fields) {
            if (!field.getType().equals(targetClass)) continue;
            if (null == instance && !Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            list.add(field);
        }
        return list;
    }

    public static List<Object> findPrivateValue(Class<?> insClass, Object instance, Class<?> targetClass) {
        List<Field> fields = findFields(insClass, instance, targetClass);
        List<Object> list = new ArrayList<Object>();
        for (Field field : fields) {
            try {
                list.add(field.get(instance));
            } catch (Exception ignored) {
            }
        }
        return list;
    }
}

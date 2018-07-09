package com.vta.codingmobile.vtamovil.Helpers.DataAnnotations;

import com.vta.codingmobile.vtamovil.Helpers.Enumerators.ColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    /**
     * Nombre de la columna
     */
    String name() default "";

    /**
     * Tipo de columna
     */
    ColumnType type() default ColumnType.TEXT;

    /**
     * True para indicar que es PRIMARY KEY
     *
     * @return
     */
    boolean isPrimaryKey() default false;

    /**
     * True para auto-incrementar de 1 A 1.
     *
     * @return
     */
    boolean isAutoincrement() default false;
}

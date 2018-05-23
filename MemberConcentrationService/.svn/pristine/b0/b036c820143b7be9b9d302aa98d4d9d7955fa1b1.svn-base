package com.cmts.xm.dao.annotation;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Is used to specify a mapped column for a persistent property or field.
 * If no Column annotation is specified, the default values are applied.
 * <p> Examples:
 *
 * <blockquote><pre>
 * Example 1:
 * &#064;Column(name="DESC", nullable=false, length=512)
 * public String getDescription() { return description; }
 *
 * Example 2:
 * &#064;Column(name="DESC",
 *         columnDefinition="CLOB NOT NULL",
 *         table="EMP_DETAIL")
 * &#064;Lob
 * public String getDescription() { return description; }
 *
 * Example 3:
 * &#064;Column(name="ORDER_COST", updatable=false, precision=12, scale=2)
 * public BigDecimal getCost() { return cost; }
 *
 * </pre></blockquote>
 *
 *
 * @since Java Persistence 1.0
 */ 
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Column {

    /**
     * (Optional) The name of the column. Defaults to 
     * the property or field name.
     */
    String value() default "";
 }
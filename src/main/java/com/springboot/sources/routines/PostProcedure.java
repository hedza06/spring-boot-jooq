/*
 * This file is generated by jOOQ.
*/
package com.springboot.sources.routines;


import com.springboot.sources.Jooq;

import javax.annotation.Generated;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostProcedure extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1585886203;

    /**
     * The parameter <code>jooq.post_procedure.title</code>.
     */
    public static final Parameter<String> TITLE = createParameter("title", org.jooq.impl.SQLDataType.VARCHAR.length(64), false, false);

    /**
     * The parameter <code>jooq.post_procedure.post_count</code>.
     */
    public static final Parameter<Integer> POST_COUNT = createParameter("post_count", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public PostProcedure() {
        super("post_procedure", Jooq.JOOQ);

        addInParameter(TITLE);
        addOutParameter(POST_COUNT);
    }

    /**
     * Set the <code>title</code> parameter IN value to the routine
     */
    public void setTitle(String value) {
        setValue(TITLE, value);
    }

    /**
     * Get the <code>post_count</code> parameter OUT value from the routine
     */
    public Integer getPostCount() {
        return get(POST_COUNT);
    }
}

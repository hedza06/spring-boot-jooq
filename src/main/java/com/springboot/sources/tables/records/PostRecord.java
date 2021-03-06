/*
 * This file is generated by jOOQ.
*/
package com.springboot.sources.tables.records;


import com.springboot.sources.tables.Post;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


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
public class PostRecord extends UpdatableRecordImpl<PostRecord> implements Record7<UInteger, String, UInteger, Timestamp, Timestamp, Timestamp, Boolean> {

    private static final long serialVersionUID = -1588677670;

    /**
     * Setter for <code>jooq.post.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>jooq.post.id</code>.
     */
    @NotNull
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>jooq.post.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>jooq.post.title</code>.
     */
    @NotNull
    @Size(max = 128)
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>jooq.post.user_id</code>.
     */
    public void setUserId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>jooq.post.user_id</code>.
     */
    @NotNull
    public UInteger getUserId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>jooq.post.published_at</code>.
     */
    public void setPublishedAt(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>jooq.post.published_at</code>.
     */
    public Timestamp getPublishedAt() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>jooq.post.created_at</code>.
     */
    public void setCreatedAt(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>jooq.post.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>jooq.post.updated_at</code>.
     */
    public void setUpdatedAt(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>jooq.post.updated_at</code>.
     */
    public Timestamp getUpdatedAt() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>jooq.post.is_active</code>.
     */
    public void setIsActive(Boolean value) {
        set(6, value);
    }

    /**
     * Getter for <code>jooq.post.is_active</code>.
     */
    public Boolean getIsActive() {
        return (Boolean) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<UInteger, String, UInteger, Timestamp, Timestamp, Timestamp, Boolean> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<UInteger, String, UInteger, Timestamp, Timestamp, Timestamp, Boolean> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Post.POST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Post.POST.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return Post.POST.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return Post.POST.PUBLISHED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return Post.POST.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return Post.POST.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field7() {
        return Post.POST.IS_ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getPublishedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value7() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value2(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value3(UInteger value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value4(Timestamp value) {
        setPublishedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value5(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value6(Timestamp value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value7(Boolean value) {
        setIsActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord values(UInteger value1, String value2, UInteger value3, Timestamp value4, Timestamp value5, Timestamp value6, Boolean value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostRecord
     */
    public PostRecord() {
        super(Post.POST);
    }

    /**
     * Create a detached, initialised PostRecord
     */
    public PostRecord(UInteger id, String title, UInteger userId, Timestamp publishedAt, Timestamp createdAt, Timestamp updatedAt, Boolean isActive) {
        super(Post.POST);

        set(0, id);
        set(1, title);
        set(2, userId);
        set(3, publishedAt);
        set(4, createdAt);
        set(5, updatedAt);
        set(6, isActive);
    }
}

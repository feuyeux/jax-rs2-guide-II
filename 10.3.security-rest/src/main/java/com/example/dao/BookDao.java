package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.example.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>BookDao class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@Repository
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * <p>Constructor for BookDao.</p>
     */
    public BookDao() {
    }

    /**
     * <p>findById.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    public Book findById(final Integer id) {
        Assert.notNull(id);
        try {
            return entityManager.find(Book.class, id);
        } catch (final Exception e) {
            //BookDao.LOGGER.error(e);
            return null;
        }
    }

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Book> findAll() {
        return findAll(false, 0, 0);
    }

    /**
     * <p>findAll.</p>
     *
     * @param isPaging    a boolean.
     * @param firstResult a int.
     * @param maxResults  a int.
     * @return a {@link java.util.List} object.
     */
    public List<Book> findAll(final boolean isPaging, final int firstResult, final int maxResults) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        final TypedQuery<Book> q = entityManager.createQuery(cq);
        if (isPaging) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    /**
     * <p>remove.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a boolean.
     */
    @Transactional
    public boolean remove(final Integer bookId) {
        final Book book0 = findById(bookId);
        if (book0 != null) {
            entityManager.remove(book0);
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>store.</p>
     *
     * @param entity a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Transactional
    public Book store(final Book entity) {
        return entityManager.merge(entity);
    }

    /**
     * <p>save.</p>
     *
     * @param entity a {@link com.example.domain.Book} object.
     */
    @Transactional
    public void save(final Book entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public void update(final Book entity) {
        String jpql = "UPDATE Book b SET b.bookName='" + entity.getBookName()
            + "', b.publisher='" + entity.getPublisher()
            + "' WHERE b.bookId=" + entity.getBookId();
        entityManager.createQuery(jpql).executeUpdate();
    }

}

package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Document save(Document document) {
        return this.executeTransaction((em) -> {
            em.persist(document);
            return document;
        });
    }

    @Override
    public List<Document> findAll() {
        return this.executeTransaction((em) -> {
            return em.createNativeQuery("SELECT * FROM documents", Document.class)
                    .getResultList();
        });
    }

    @Override
    public Document findById(String id) {
        return this.executeTransaction((em) -> {
            return (Document) em.createNativeQuery("SELECT * FROM documents WHERE id = '" + id + "'",
                    Document.class)
                    .getSingleResult();
        });
    }

    @Override
    public void deleteDocumentById(String id) {
        this.executeTransaction(
                (em) -> {
                    return em.createQuery("DELETE FROM Document where id=:id")
                            .setParameter("id",id)
                            .executeUpdate();
                }
        );
    }
}

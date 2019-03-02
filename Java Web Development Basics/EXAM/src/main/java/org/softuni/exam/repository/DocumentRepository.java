package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.Document;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
public interface DocumentRepository extends GenericRepository<Document,String> {
    void deleteDocumentById(String id);
}

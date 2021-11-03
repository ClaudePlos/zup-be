package pl.rekeep.app.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.rekeep.app.domain.Attachment;

public interface AttachmentManager extends PagingAndSortingRepository<Attachment, Long> {
}

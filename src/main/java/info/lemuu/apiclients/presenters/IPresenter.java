package info.lemuu.apiclients.presenters;

import info.lemuu.apiclients.entity.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lemuel Brenner
 */
public interface IPresenter<T extends IEntity, R> {

    ResponseEntity<Page<R>> index( Pageable pageable);

    ResponseEntity<List<R>> show(HttpServletRequest request);

    ResponseEntity<R> show(Long id);

    ResponseEntity<T> store(T client);

    ResponseEntity<T> update(Long id, T form);

    ResponseEntity<?> delete(Long id);

}

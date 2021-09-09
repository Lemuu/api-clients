package info.lemuu.apiclients.presenters;

import info.lemuu.apiclients.model.IModel;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lemuel Brenner
 */
public interface IPresenter<T extends IModel> {

    List<ResponseEntity<T>> index(HttpServletRequest request);

    ResponseEntity<T> show(Long id);

    ResponseEntity<T> store(T client);

    ResponseEntity<T> update(Long id, T form);

    ResponseEntity<?> delete(Long id);

}

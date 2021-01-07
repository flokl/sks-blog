package at.technikumwien;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {
	private final EntityManager em;

	public CustomJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
	}

	@Override
	public <S extends T> S save(S entity) {
		return em.merge(entity);
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {

		Assert.notNull(entities, "Entities must not be null!");

		List<S> result = new ArrayList<S>();

		for (S entity : entities) {
			result.add(em.merge(entity));
		}

		return result;
	}
}
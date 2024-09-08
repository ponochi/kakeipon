package org.panda.systems.kakeipon.domain.service.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private SpecificationRepository specificationRepository;

  public Long getMaxSpecificationId(Long specificationGroupId) {
    return specificationRepository.getMaxId(specificationGroupId);
  }

  public List<Specification> findAll() {
    return specificationRepository.findAll();
  }

  public List<Specification> findBySpecificationGroupId(Long specificationGroupId) {
    return specificationRepository.findBySpecificationGroupId(specificationGroupId);
  }

  public List<org.panda.systems.kakeipon.app.spec.SpecificationForm> findBySpecificationGroupIdToForm(Long specificationGroupId) {
    List<Specification> specifications = specificationRepository.findBySpecificationGroupId(specificationGroupId);
    org.panda.systems.kakeipon.app.spec.SpecificationForm specificationForm = new org.panda.systems.kakeipon.app.spec.SpecificationForm();

    List<org.panda.systems.kakeipon.app.spec.SpecificationForm> forms = new ArrayList<>();
    for (Specification specification : specifications) {
      forms.add(
          specificationForm.setSpecificationToForm(specification));
    }
    return forms;
  }

  @Transactional
  public Specification saveAndFlush(Specification entity) {
    return specificationRepository.saveAndFlush(entity);
  }
}

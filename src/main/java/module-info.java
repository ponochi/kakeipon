module org.panda.systems.kakeipon {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.beans;
  requires spring.tx;
  requires spring.data.jpa;
  requires jakarta.persistence;
  requires jakarta.validation;
  requires spring.web;
  exports org.panda.systems.kakeipon;
  opens org.panda.systems.kakeipon;
  opens org.panda.systems.kakeipon.app.user;
  opens org.panda.systems.kakeipon.domain.model;
  opens org.panda.systems.kakeipon.domain.repository.user;
  opens org.panda.systems.kakeipon.domain.service.user;
}

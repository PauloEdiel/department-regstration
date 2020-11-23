package com.department.registration.service;

import com.department.registration.model.DepartmentEntity;
import com.department.registration.repository.DepartmentRepository;
import com.department.registration.request.DepartmentRequest;
import com.department.registration.response.DefaultResponse;
import com.department.registration.response.DepartmentResponse;
import com.department.registration.response.ListDepartmentResponse;
import com.department.registration.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureDataJpa
public class DepartmentServiceTest {

    @Autowired
    DepartmentRepository repository;

    private final String MESSAGE_SUCCESS = "Process executed successfully";
    private final String MESSAGE_NOT_FOUND = "Resource Not Found";
    private final String STATUS_SUCCESS = "SUCCESS";
    private final String STATUS_NOT_FOUND = "NOT_FOUND";



    @Test
    public void shouldReturnResponseDefault() {
        DepartmentService departmentService = new DepartmentService(repository);

        DefaultResponse response = departmentService.toSuccessResponse();

        Assert.assertEquals(STATUS_SUCCESS, response.getStatus());
        Assert.assertEquals(MESSAGE_SUCCESS, response.getMessages());
    }

    @Test
    public void shouldReturnDepartmentResponseFindById() {
        DepartmentService service = new DepartmentService(repository);

        repository.save(this.createEntity());
        long id = service.findAll().getDepartments().get(0).getId();
        DepartmentResponse response = service.findById(id);

        Assert.assertNotNull(response);
        Assert.assertEquals(id, response.getDepartmentEntity().getId());
        Assert.assertEquals("Dev Test", response.getDepartmentEntity().getName());
        Assert.assertEquals(response.getDepartmentEntity().getLocal(), "Itaú");
        Assert.assertEquals(response.getDepartmentEntity().getBoard(), "Tecnologia");
    }

    @Test
    public void shouldReturnListDepartmentResponse() {
        DepartmentService service = new DepartmentService(repository);

        repository.save(this.createEntity());
        repository.save(this.createEntity());

        ListDepartmentResponse listDepartmentResponse = service.findAll();
        DepartmentEntity entity = listDepartmentResponse.getDepartments().get(0);
        long id = entity.getId();

        Assert.assertEquals(2, listDepartmentResponse.getDepartments().size());
        Assert.assertEquals(id, entity.getId());
        Assert.assertEquals("Dev Test", entity.getName());
        Assert.assertEquals(entity.getLocal(), "Itaú");
        Assert.assertEquals(entity.getBoard(), "Tecnologia");
    }

    @Test
    public void shouldDeleteDepartmentById() {
        DepartmentService departmentService = new DepartmentService(repository);

        repository.save(this.createEntity());
        repository.save(this.createEntity());
        List<DepartmentEntity> departmentEntities = repository.findAll();
        Assert.assertEquals(2, departmentEntities.size());
        long id = departmentEntities.get(0).getId();

        departmentService.deleteById(id);
        departmentEntities = repository.findAll();
        Assert.assertEquals(1, departmentEntities.size());
        Assert.assertNull(repository.findById(id));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldReturnExceptionWhenTryDeleteDepartmentByIdIfIdNotExists() {
        DepartmentService departmentService = new DepartmentService(repository);

        repository.save(this.createEntity());
        repository.save(this.createEntity());

        departmentService.deleteById(10L);
    }

    @Test
    public void shouldReturnExceptionWhenTryFindByIdIfIdNotExists() {
        DepartmentService departmentService = new DepartmentService(repository);

        repository.save(this.createEntity());
        repository.save(this.createEntity());

        DepartmentEntity entity = repository.findById(10L);
        Assert.assertNull(entity);
    }

    @Test
    public void shouldConvertRequestForEntityAndSaveInDatabase() {
        DepartmentService departmentService = new DepartmentService(repository);
        DepartmentRequest request = createRequest();

        DepartmentResponse response = departmentService.save(request);
        long id = response.getDepartmentEntity().getId();

        Assert.assertNotNull(response);

        DepartmentEntity entity = repository.findById(id);

        Assert.assertEquals(entity.getId(), response.getDepartmentEntity().getId());
    }

    @Test
    public void shouldUpdateDepartmentEntity() {
        DepartmentService service = new DepartmentService(repository);

        repository.save(this.createEntity());
        DepartmentEntity oldEntity =  repository.findAll().get(0);
        String oldName = oldEntity.getName();
        String oldLocal = oldEntity.getLocal();
        String oldCity = oldEntity.getCity();
        String oldState = oldEntity.getState();
        long id = oldEntity.getId();

        DepartmentResponse response = service.updateEntity(createEntityWithId(id));

        Assert.assertEquals(response.getDepartmentEntity().getId(), oldEntity.getId());
        Assert.assertEquals("Dev Test Update", response.getDepartmentEntity().getName());
        Assert.assertEquals("Itaú Update", response.getDepartmentEntity().getLocal());
        Assert.assertEquals("São Paulo Update", response.getDepartmentEntity().getCity());

    }

    @Test
    public void shouldReturnNotFoundResponse() {
        DepartmentService service = new DepartmentService(repository);

        DefaultResponse response = service.toNotFoundResponse();

        Assert.assertNotNull(response);
        Assert.assertEquals(MESSAGE_NOT_FOUND, response.getMessages());
        Assert.assertEquals(STATUS_NOT_FOUND, response.getStatus());

    }


    private DepartmentEntity createEntity() {
        return DepartmentEntity.builder()
            .name("Dev Test")
            .local("Itaú")
            .city("São Paulo")
            .state("SP")
            .board("Tecnologia")
            .build();
    }

    private DepartmentEntity createEntityWithId(long id) {
        return DepartmentEntity.builder()
            .id(id)
            .name("Dev Test Update")
            .local("Itaú Update")
            .city("São Paulo Update")
            .state("SP")
            .board("Tecnologia")
            .build();
    }

    private DepartmentRequest createRequest() {
        return DepartmentRequest.builder()
            .name("Dev Test")
            .local("Itaú")
            .city("São Paulo")
            .state("SP")
            .board("Tecnologia")
            .build();
    }
}

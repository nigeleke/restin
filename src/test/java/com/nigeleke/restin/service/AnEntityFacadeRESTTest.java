package com.nigeleke.restin.service;

import com.nigeleke.restin.model.AnEntity;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.RuntimeDelegate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnEntityFacadeRESTTest {

    @Before
    public void setUp() {
        RuntimeDelegate.setInstance(runtimeDelegate);
    }

    @Test
    public void testCreate() throws Exception {
        AnEntity anEntity = mock(AnEntity.class);
        UriBuilder uriBuilder = mock(UriBuilder.class);
        URI uri = URI.create("http://restin.local/rest/anentity/");
        ResponseBuilder responseBuilder = mock(ResponseBuilder.class);
        Response response = mock(Response.class);

        when((uriInfo).getRequestUriBuilder()).thenReturn(uriBuilder);
        when((uriBuilder).path(isA(String.class))).thenReturn(uriBuilder);
        when((uriBuilder).build()).thenReturn(uri);
        when((runtimeDelegate).createResponseBuilder()).thenReturn(responseBuilder);
        when((runtimeDelegate).createUriBuilder()).thenReturn(uriBuilder);
        when((responseBuilder).status(Response.Status.CREATED)).thenReturn(responseBuilder);
        when(Response.status(Response.Status.CREATED)).thenReturn(responseBuilder);
        when((responseBuilder).location(uri)).thenReturn(responseBuilder);
        when((responseBuilder).entity(any(AnEntity.class))).thenReturn(responseBuilder);
        when((responseBuilder).contentLocation(uri)).thenReturn(responseBuilder);
        when((responseBuilder).build()).thenReturn(response);

        Response result = instance.create(anEntity);

        verify(em).persist(anEntity);
        verify(em).flush();

        assertEquals(result, response);
    }

    @Test
    public void testEdit() throws Exception {
        AnEntity anEntity = mock(AnEntity.class);

        instance.edit(anEntity);

        verify(em).merge(anEntity);
    }

    @Test
    public void testRemove() throws Exception {
        AnEntity anEntity = mock(AnEntity.class);
        Long anEntityId = Long.valueOf(19570310L);

        when((anEntity).getId()).thenReturn(anEntityId);
        when((em).find(AnEntity.class, anEntityId.longValue())).thenReturn(anEntity);
        when((em).merge(anEntity)).thenReturn(anEntity);

        instance.remove(anEntity.getId());

        verify(em).remove(anEntity);
    }

    @Test
    public void testFind() throws Exception {
        AnEntity anEntity = mock(AnEntity.class);
        Long anEntityId = Long.valueOf(19570310L);

        when((anEntity).getId()).thenReturn(anEntityId);
        when((em).find(AnEntity.class, anEntityId.longValue())).thenReturn(anEntity);

        AnEntity result = instance.find(anEntity.getId());
        assertEquals(result, anEntity);
    }

    @Test
    public void testFindAll() throws Exception {
        AnEntity anEntity1 = mock(AnEntity.class, "anEntity1");
        AnEntity anEntity2 = mock(AnEntity.class, "anEntity2");
        List<AnEntity> anEntitys = Arrays.asList(anEntity1, anEntity2);

        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        TypedQuery typedQuery = mock(TypedQuery.class);

        when((em).getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when((criteriaBuilder).createQuery()).thenReturn(criteriaQuery);
        when((em).createQuery(criteriaQuery)).thenReturn(typedQuery);
        when((typedQuery).getResultList()).thenReturn(anEntitys);

        List<AnEntity> result = instance.findAll();
        assertEquals(result, anEntitys);
    }

    @Test
    public void testFindRange() throws Exception {
        AnEntity anEntity1 = mock(AnEntity.class, "anEntity1");
        AnEntity anEntity2 = mock(AnEntity.class, "anEntity2");
        List<AnEntity> anEntitys = Arrays.asList(anEntity1, anEntity2);

        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        TypedQuery typedQuery = mock(TypedQuery.class);

        when((em).getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when((criteriaBuilder).createQuery()).thenReturn(criteriaQuery);
        when((em).createQuery(criteriaQuery)).thenReturn(typedQuery);
        when((typedQuery).getResultList()).thenReturn(anEntitys);

        Integer from = Integer.MIN_VALUE;
        Integer to = Integer.MAX_VALUE;
        List<AnEntity> result = instance.findRange(from, to);

        assertEquals(result, anEntitys);
    }

    @Test
    public void testCountREST() throws Exception {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        TypedQuery typedQuery = mock(TypedQuery.class);
        Long count = Long.valueOf(19570310L);

        when((em).getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when((criteriaBuilder).createQuery()).thenReturn(criteriaQuery);
        when((em).createQuery(criteriaQuery)).thenReturn(typedQuery);
        when((typedQuery).getSingleResult()).thenReturn(count);

        String result = instance.countREST();
        assertEquals(result, count.toString());
    }

    @Mock
    private EntityManager em;

    @Mock
    private UriInfo uriInfo;

    @Mock
    private RuntimeDelegate runtimeDelegate;

    @InjectMocks
    private AnEntityFacadeREST instance;
}

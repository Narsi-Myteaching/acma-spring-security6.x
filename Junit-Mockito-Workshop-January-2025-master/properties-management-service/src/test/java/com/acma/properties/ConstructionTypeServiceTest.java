package com.acma.properties;

import com.acma.properties.beans.ConstructionTypeBean;
import com.acma.properties.models.ConstructionType;
import com.acma.properties.repo.ConstructionTypeRepo;
import com.acma.properties.services.ConstructionTypeServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConstructionTypeServiceTest {

    @InjectMocks
    private ConstructionTypeServiceImpl underTesting;

    @Mock
    private ConstructionTypeRepo mockRepo;

    @Mock
    private ModelMapper mockModelMapper;

    ConstructionType constructionTypeModel;

    ConstructionTypeBean expectedConstructionTypeBean;

    @BeforeEach
    public void setUp(){
        constructionTypeModel = new ConstructionType();
        constructionTypeModel.setId("123");
        constructionTypeModel.setType("Wood");

        expectedConstructionTypeBean = new ConstructionTypeBean();
        expectedConstructionTypeBean.setId("123");
        expectedConstructionTypeBean.setType("wood");
    }

    @DisplayName("Create_ConstructionType")
    @Test
    public void test_createConstructionType_returns_createdConstructionType (){

        // Arrange
        when(mockModelMapper.map(any(ConstructionTypeBean.class), any())).thenReturn(constructionTypeModel);
        when(mockRepo.save(any(ConstructionType.class))).thenReturn(constructionTypeModel);
        when(mockModelMapper.map(any(ConstructionType.class), any())).thenReturn(expectedConstructionTypeBean);

        // Act
        ConstructionTypeBean savedConstructionTypeBean =  underTesting.createConstructionType(expectedConstructionTypeBean);

        // Assert
        assertNotNull(savedConstructionTypeBean);
        assertEquals(expectedConstructionTypeBean.getId(), savedConstructionTypeBean.getId());

        verify(mockModelMapper, times(2)).map(any(),any());
        verify(mockRepo).save(any(ConstructionType.class));
    }

    @DisplayName("Test Create Construction Throws Exception")
    @Test
    public void test_createConstructionType_throws_Exception(){
        ConstructionType constructionTypeModelForEx = new ConstructionType();
        constructionTypeModelForEx.setType("Wood");

        // Arrange
        when(mockModelMapper.map(any(ConstructionTypeBean.class), any())).thenReturn(constructionTypeModel);
        when(mockRepo.save(any(ConstructionType.class))).thenReturn(constructionTypeModelForEx);

        // Act
        RuntimeException runtimeException = assertThrows(RuntimeException.class, ()-> underTesting.createConstructionType(expectedConstructionTypeBean));

        // Assert
        assertNotNull(runtimeException);

        verify(mockModelMapper, times(1)).map(any(),any());
        verify(mockRepo).save(any(ConstructionType.class));
    }

    @DisplayName("test_getConstructionByType_returnsCreatedConstructionType")
    @Test
    public void test_getConstructionByType_returnsCreatedConstructionType(){
        // Arrange
        List<ConstructionType> constructionTypeList = new ArrayList<>();
        constructionTypeList.add(constructionTypeModel);

        when(mockRepo.fetchCoonstructionByType(anyString())).thenReturn(constructionTypeList);
        when(mockModelMapper.map(any(ConstructionType.class), any())).thenReturn(expectedConstructionTypeBean);

        // Act
        ConstructionTypeBean mockConstructionTypeBean =  underTesting.getConstructionByType("basement");

        // Assert
        assertNotNull(mockConstructionTypeBean);
        assertEquals(expectedConstructionTypeBean.getType(), mockConstructionTypeBean.getType());

        verify(mockRepo).fetchCoonstructionByType(anyString());
        verify(mockModelMapper).map(any(),any());


    }
}

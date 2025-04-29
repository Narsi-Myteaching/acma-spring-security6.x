package com.acma.properties;

import com.acma.properties.beans.PropertyInfoBean;
import com.acma.properties.models.PropertyInfo;
import com.acma.properties.repo.PropertyInfoRepo;
import com.acma.properties.services.PropertyInfoServiceImpl;
import com.acma.properties.services.SearchService;
import com.acma.properties.utills.AppUtils;
import org.junit.jupiter.api.*;
import  static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import  static org.mockito.Mockito.*;

import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyInfoServiceTest {

    @InjectMocks
    private PropertyInfoServiceImpl underTesting;

    @Mock
    private PropertyInfoRepo mockPropertyInfoRepo;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private SearchService mockSearchService;

    private MockedStatic<AppUtils> mockAppUtils;

    private PropertyInfo expectedPropertyInfoModel;

    private PropertyInfoBean expectedPropertyInfoBean;

    @BeforeEach
    public void setUp(){
        expectedPropertyInfoModel = new PropertyInfo();
        expectedPropertyInfoModel.setId("123");

        expectedPropertyInfoBean = new PropertyInfoBean();
        expectedPropertyInfoBean.setId("123");

        mockAppUtils = mockStatic(AppUtils.class);
    }

    @AfterEach
    public void testDown(){
        expectedPropertyInfoModel = null;
        expectedPropertyInfoBean = null;

        mockAppUtils.close();
    }

    @DisplayName("test_createProperty_returnsCreatedProperty")
    @Test
    public void test_createProperty_returns_createdProperty(){
        // Arrange
        when(mockModelMapper.map(any(PropertyInfoBean.class), any())).thenReturn(expectedPropertyInfoModel);
        when(mockPropertyInfoRepo.save(any(PropertyInfo.class))).thenReturn(expectedPropertyInfoModel);
        mockAppUtils.when(() -> AppUtils.mapDomainToBean(any(PropertyInfo.class), any(ModelMapper.class))).thenReturn(expectedPropertyInfoBean);


        // Act
        PropertyInfoBean mockedPropInfoBean =  underTesting.createProperty(expectedPropertyInfoBean);

        assertNotNull(mockedPropInfoBean);
        assertEquals(expectedPropertyInfoBean.getId(), mockedPropInfoBean.getId());

        verify(mockModelMapper).map(any(),any());
        verify(mockPropertyInfoRepo).save(any(PropertyInfo.class));

        mockAppUtils.verify(()-> AppUtils.mapDomainToBean(any(), any()));
    }


    @DisplayName("test_searchPropertiesByZipCode_returns_matched_properties")
    @Test
    public void test_searchPropertiesByZipCode_returns_matched_properties(){
        // Arrange
        List<PropertyInfoBean> expectedPropertiesList = new ArrayList<>();
        expectedPropertiesList.add(expectedPropertyInfoBean);

        when(mockSearchService.searchByName(anyString())).thenReturn(expectedPropertiesList);


        // Act
       List<PropertyInfoBean> mockedPropsList =  underTesting.searchPropertiesByZipCode("1234");

       // Assert
       assertNotNull(mockedPropsList);
       assertEquals(expectedPropertiesList.size(), mockedPropsList.size());

       verify(mockSearchService).searchByName(anyString());
    }

}

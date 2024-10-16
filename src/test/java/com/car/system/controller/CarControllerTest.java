package com.car.system.controller;

import com.car.system.entity.Car;
import com.car.system.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private MockMvc mockMvc;
    private Car car;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();

        car = new Car();
        car.setId(1L);
    }

    @Test
    void testStartCar() throws Exception {
        when(carService.startCar(1L)).thenReturn(car);

        mockMvc.perform(post("/cars/1/start")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(carService).startCar(1L);
    }

    @Test
    void testStopCar() throws Exception {
        when(carService.stopCar(1L)).thenReturn(car);

        mockMvc.perform(post("/cars/1/stop")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(carService).stopCar(1L);
    }

    @Test
    void testRefuelCar() throws Exception {
        when(carService.refuelCar(1L, 10.0)).thenReturn(car);

        mockMvc.perform(post("/cars/1/refuel")
                        .param("amount", "10.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(carService).refuelCar(1L, 10.0);
    }

    @Test
    void testGetFuelLevel() throws Exception {
        when(carService.getFuelLevel(1L)).thenReturn(50.0);

        mockMvc.perform(get("/cars/1/fuel-level")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0"));

        verify(carService).getFuelLevel(1L);
    }
}
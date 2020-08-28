package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;


class GetAllFarmsUseCaseTest {

    @Mock
    private FarmRepository farmRepository;

    private GetAllFarmsUseCase getAllFarmsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        getAllFarmsUseCase = spy(new GetAllFarmsUseCase(farmRepository));
    }

    @AfterEach
    void tearDown() {
        getAllFarmsUseCase = null;
    }

    @Test
    void execute_allGood_shouldPass() {
        List<Farm> farmList = new LinkedList<>();
        when(farmRepository.findAll()).thenReturn(farmList);

        getAllFarmsUseCase.execute();

        verify(farmRepository, times(1)).findAll();
    }
}

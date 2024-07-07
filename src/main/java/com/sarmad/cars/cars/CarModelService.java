package com.sarmad.cars.cars;

import com.sarmad.cars.models.CarModel;
import com.sarmad.cars.models.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;

    public CarModel createCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public List<CarModel> getCarModelsByIds(List<String> ids) {
        // TODO: Check the cache first
        return carModelRepository.findAllById(ids);
    }

    public CarModel getCarModel(String id) {
        return carModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Car model not found"));
    }

    public CarModel updateCarModel(String id, CarModel carModel) {
        var existingCarModel = carModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Car model not found"));
        existingCarModel.setModelName(carModel.getModelName());
        existingCarModel.setType(carModel.getType());
        existingCarModel.setManufacturerYear(carModel.getManufacturerYear());
        return carModelRepository.save(existingCarModel);
    }

    public void deleteCarModel(String id) {
        carModelRepository.deleteById(id);
    }
}

package com.etiya.ReCapProject.business.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.CarImageSearchListDto;
import com.etiya.ReCapProject.business.requests.carImageRequests.CreateCarImageRequest;
import com.etiya.ReCapProject.business.requests.carImageRequests.DeleteCarImageRequest;
import com.etiya.ReCapProject.business.requests.carImageRequests.UpdateCarImageRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.CarImage;

@Service
//@ConfigurationProperties
public class CarImageManager implements CarImageService {
	
	private CarImageDao carImageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private Environment environment;

	@Autowired
	public CarImageManager(CarImageDao carImageDao, ModelMapperService modelMapperService, CarService carService, Environment environment) {
		this.carImageDao = carImageDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.environment = environment;
	}



//	@Value("${imagePath}")
//	private String path;



	@Override
	public DataResult<List<CarImageSearchListDto>> getAll() {
		List<CarImage> carImages = this.carImageDao.findAll();
		List<CarImageSearchListDto> result = carImages.stream()
				.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarImageSearchListDto>>(result, Messages.CARIMAGELIST);
	}



	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {
		Result result = BusinessRules.run(checkIfCarIdExists(createCarImageRequest.getCarId()),checkNumberOfCarImages( createCarImageRequest.getCarId()),
				checkIfImageTypeIsValid(createCarImageRequest.getFile()));
		if (result != null) {
			return result;
		}

		generateImage(createCarImageRequest.getFile());
		CarImage carImage = modelMapperService.forRequest().map(createCarImageRequest, CarImage.class);
		carImage.setDate(LocalDate.now());
		carImage.setImagePath(createCarImageRequest.getFile().getBytes());
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CARIMAGEADD);
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		Result result = BusinessRules.run(isCarImageExists(deleteCarImageRequest.getId()));
		if (result != null) {
			return result;
		}
		var carImage = this.carImageDao.getById(deleteCarImageRequest.getId());
		this.carImageDao.delete(carImage);
		return new SuccessResult(Messages.CARIMAGEDELETE);
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
		Result result = BusinessRules.run(checkIfCarIdExists(updateCarImageRequest.getCarId()),isCarImageExists(updateCarImageRequest.getId()), checkIfImageTypeIsValid(updateCarImageRequest.getFile()));
		if (result != null) {
			return result;
		}
		CarImage carImage = modelMapperService.forRequest().map(updateCarImageRequest, CarImage.class);
		carImage.setDate(LocalDate.now());
		carImage.setImagePath(generateImage(updateCarImageRequest.getFile()).toString().getBytes());
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CARIMAGEUPDATE);
	}

	private File generateImage(MultipartFile file) throws IOException {
		String path = environment.getProperty("imagePath","defaultImagePath");
		String imagePathGuid = java.util.UUID.randomUUID().toString();

		//"C:\\Users\\doruk.senay\\Desktop\\Photos\\"
		File imageFile = new File(path+"/"+ imagePathGuid + "."
				+ file.getContentType().substring(file.getContentType().indexOf("/") + 1));
		imageFile.createNewFile();
		FileOutputStream outputImage = new FileOutputStream(imageFile);
		outputImage.write(file.getBytes());
		outputImage.close();
		return imageFile;
	}

	private Result checkNumberOfCarImages(int carId) {
		if (this.carImageDao.countCarImagesByCar_Id(carId) >= 5) {
			return new ErrorResult(Messages.CARIMAGELIMITERROR);
		}
		return new SuccessResult();
	}

	public DataResult<List<CarImageSearchListDto>> getCarImagesByCarId(int id) {
		String defaultPath = environment.getProperty("defaultImagePath");
		if (this.carImageDao.getByCar_Id(id).isEmpty()) {
			CarImageSearchListDto carImageSearchListDto = new CarImageSearchListDto();
			carImageSearchListDto
					.setImagePath(defaultPath);
			List<CarImageSearchListDto> carImages = new ArrayList<CarImageSearchListDto>();
			carImages.add(carImageSearchListDto);
			return new SuccessDataResult<List<CarImageSearchListDto>>(carImages,Messages.CARIMAGELIST);
		}
		List<CarImage> carImages = this.carImageDao.getByCar_Id(id);
		List<CarImageSearchListDto> result = carImages.stream()
				.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarImageSearchListDto>>(result, Messages.CARIMAGELIST);
	}

	private Result isCarImageExists(int carImageId) {
		var result = this.carImageDao.existsById(carImageId);
		if (!result) {
			return new ErrorResult(Messages.CARIMAGENOTFOUND);
		}
		return new SuccessResult();
	}

	private Result checkImageIsNull(MultipartFile file) {
		if (file == null || file.isEmpty() || file.getSize() == 0) {
			return new ErrorResult(Messages.CARIMAGETYPEERROR);
		}
		
		
		return new SuccessResult();
	}

	private Result checkIfImageTypeIsValid(MultipartFile multipartFile) {
		if (!(checkImageIsNull(multipartFile).isSuccess())) {
			return new ErrorResult(this.checkImageIsNull(multipartFile).getMessage());
		}
		if (!multipartFile.getContentType().toString().substring(multipartFile.getContentType().indexOf("/") + 1)
				.equals("jpeg")
				&& !multipartFile.getContentType().toString().substring(multipartFile.getContentType().indexOf("/") + 1)
						.equals("jpg")
				&& !multipartFile.getContentType().toString().substring(multipartFile.getContentType().indexOf("/") + 1)
						.equals("png")) {
			return new ErrorResult(Messages.CARIMAGETYPEERROR);
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarIdExists(int id) {
		var result = this.carService.isCarExists(id);
		if (!result.isSuccess()) {
			return new ErrorResult(Messages.CARNOTFOUND);
		}
		return new SuccessResult();
		
	}

}

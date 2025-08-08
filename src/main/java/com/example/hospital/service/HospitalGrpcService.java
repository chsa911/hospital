
package com.example.hospital.service;

import com.example.hospital.entity.Hospital;
import com.example.hospital.repository.HospitalRepository;
import com.example.hospital.grpc.HospitalServiceGrpc;
import com.example.hospital.grpc.HospitalRequest;
import com.example.hospital.grpc.HospitalResponse;
import com.example.hospital.grpc.HospitalId;
import com.example.hospital.grpc.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class HospitalGrpcService extends HospitalServiceGrpc.HospitalServiceImplBase {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void createHospital(HospitalRequest request, StreamObserver<HospitalResponse> responseObserver) {
        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setAddress(request.getAddress());
        hospital = hospitalRepository.save(hospital);

        HospitalResponse response = HospitalResponse.newBuilder()
                .setId(hospital.getId())
                .setName(hospital.getName())
                .setAddress(hospital.getAddress())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteHospital(HospitalId request, StreamObserver<Empty> responseObserver) {
        hospitalRepository.deleteById(request.getId());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}

package com.bukucontoh.bukucontoh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.bukucontoh.bukucontoh.model.BukuContoh;
import com.bukucontoh.bukucontoh.repository.BukuRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/buku")
public class BukuController{

    @Autowired
    BukuRepository bukuRepository ;

    @PostMapping(value="buku")
    public ResponseEntity<BukuContoh> createBuku(@RequestBody BukuContoh bukuContoh){
        try{
            BukuContoh _bukuContoh = bukuRepository
                                    .save(new BukuContoh(bukuContoh.getNama(),bukuContoh.getDescription(), bukuContoh.getIdrak()));
            return new ResponseEntity<>(_bukuContoh, HttpStatus.CREATED);

        }catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping(value="buku")
    public ResponseEntity<List<BukuContoh>> getAllBukuContoh(@RequestParam(required = false) String nama ){
        
        try{
            List<BukuContoh> bukucontoh= new ArrayList<BukuContoh>();
			if (nama == null)
				bukuRepository.findAll().forEach(bukucontoh::add);
			else
				bukuRepository.findByNama(nama).forEach(bukucontoh::add);

			if (bukucontoh.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
            return new ResponseEntity<>(bukucontoh, HttpStatus.OK);
            
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/buku/{id}")
    public ResponseEntity<BukuContoh> getBukuContohById(@PathVariable("id") long id){
        Optional<BukuContoh> bukucontohData = bukuRepository.findById(id);

        if(bukucontohData.isPresent()){
            return new ResponseEntity<>(bukucontohData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @PutMapping("/buku/{id}")
    public ResponseEntity<BukuContoh> updateBukuContoh(@PathVariable("id") long id, @RequestBody BukuContoh bukucontoh){
        Optional<BukuContoh> bukucontohData = bukuRepository.findById(id);

        if(bukucontohData.isPresent()){
            BukuContoh _bukucontoh = bukucontohData.get();
            _bukucontoh.setNama(bukucontoh.getNama());
            _bukucontoh.setDescription(bukucontoh.getDescription());
            _bukucontoh.setIdrak(bukucontoh.getIdrak());
            
            return new ResponseEntity<>(bukuRepository.save(_bukucontoh), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @DeleteMapping("/buku/{id}")
	public ResponseEntity<HttpStatus> deleteBukuContoh(@PathVariable("id") long id) {
		try {
			bukuRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
    }
    
    @DeleteMapping("/buku")
	public ResponseEntity<HttpStatus> deleteAllBuku() {
		try {
			bukuRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

    }
    
    // @GetMapping("/buku/nama")
    // public ResponseEntity<List<BukuContoh>> findByNama(){
    //     try{
    //         List<BukuContoh> bukucontohs = bukuRepository.findByNama(true);

    //         if (bukucontohs.isEmpty()) {
	// 			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 		}
	// 		return new ResponseEntity<>(bukucontohs, HttpStatus.OK);

    //     }catch(Exception e){
	// 		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

    //     }

    // }
    
    
    


}
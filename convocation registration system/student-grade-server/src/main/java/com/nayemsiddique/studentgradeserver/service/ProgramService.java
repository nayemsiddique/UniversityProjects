package com.nayemsiddique.studentgradeserver.service;

import com.nayemsiddique.studentgradeserver.exception.FileAlreadyExistsExceptions;
import com.nayemsiddique.studentgradeserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.studentgradeserver.model.Program;
import com.nayemsiddique.studentgradeserver.model.ProgramPrimaryKey;
import com.nayemsiddique.studentgradeserver.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {
    private ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> findAll(){
        return programRepository.findAll();
    }
    public Program findById(ProgramPrimaryKey programPrimaryKey){
        //return programRepository.findAllBy(programTitle,semesterId);
       // programRepository.
        return programRepository.findById(programPrimaryKey).get();

    }
    public Program insert(Program program){
        Optional<Program> byId = programRepository.findById(program.getProgramPrimaryKey());
        if (byId.isPresent()) {
            try {
                throw new FileAlreadyExistsExceptions(program.getProgramPrimaryKey().
                        getProgramTitle()+"."+program.getProgramPrimaryKey().getSemesterID());
            } catch (FileAlreadyExistsExceptions fileAlreadyExistsExceptions) {
                fileAlreadyExistsExceptions.printStackTrace();
                return null;
            }
        }
        return programRepository.save(program);

    }
    public boolean delete(Program program){
        Optional<Program> byId = programRepository.findById(program.getProgramPrimaryKey());
        if (byId.isPresent()){
            programRepository.delete(program);
            return true;
        }
        else{
            try {
                throw new FileDoesnotExistsException(program.getProgramPrimaryKey().
                        getProgramTitle()+"."+program.getProgramPrimaryKey().getSemesterID());
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    public Program update(ProgramPrimaryKey programPrimaryKey, Program program){
        Optional<Program> byId = programRepository.findById(program.getProgramPrimaryKey());
        if (byId.isPresent()) {
            program.setProgramPrimaryKey(programPrimaryKey);
            return programRepository.save(program);
        }
        else{
            try {
                throw new FileDoesnotExistsException(program.getProgramPrimaryKey().
                        getProgramTitle()+"."+program.getProgramPrimaryKey().getSemesterID());
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return null;
            }
        }





    }
}

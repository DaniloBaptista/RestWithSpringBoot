package br.com.erudio.controllers;

import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.math.SimpleMath;
import org.springframework.web.bind.annotation.*;
import br.com.erudio.conversoes.conversor;

@RestController
public class MathController {
    SimpleMath math = new SimpleMath();

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double greeting(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!conversor.isNumeric(numberOne) || !conversor.isNumeric(numberTwo)){
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.sum(conversor.convertToDouble(numberOne),conversor.convertToDouble(numberTwo)) ;

    }
    @RequestMapping(value="/subtrair/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtrair(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!conversor.isNumeric(numberOne) || !conversor.isNumeric(numberTwo)){
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.substrair(conversor.convertToDouble(numberOne),conversor.convertToDouble(numberTwo)) ;


    }
    @RequestMapping(value="/multiplicar/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplicar(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!conversor.isNumeric(numberOne) || !conversor.isNumeric(numberTwo)){
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.multiplicar(conversor.convertToDouble(numberOne),conversor.convertToDouble(numberTwo)) ;


    }
    @RequestMapping(value="/dividir/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double dividir(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!conversor.isNumeric(numberOne) || !conversor.isNumeric(numberTwo)){
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.dividir(conversor.convertToDouble(numberOne),conversor.convertToDouble(numberTwo)) ;


    }
    @RequestMapping(value="/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!conversor.isNumeric(numberOne) || !conversor.isNumeric(numberTwo)){
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.media(conversor.convertToDouble(numberOne),conversor.convertToDouble(numberTwo)) ;


    }
    @RequestMapping(value="/raizquadrada/{numberOne}", method = RequestMethod.GET)
    public Double raizquadrada(@PathVariable("numberOne") String numberOne) throws Exception {
        if(!conversor.isNumeric(numberOne)){
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return math.raizQuadrada(conversor.convertToDouble(numberOne)) ;


    }




}

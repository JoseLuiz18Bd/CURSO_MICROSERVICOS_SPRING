package br.com.book_service.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.book_service.model.Book;
import br.com.book_service.proxy.CambioProxy;
import br.com.book_service.repository.BookRepository;
import br.com.book_service.response.Cambio;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	/*
	 * @GetMapping("/{id}/{currency}") public Book findBookById(
	 * 
	 * @PathVariable("id") Long id,
	 * 
	 * @PathVariable("currency") String currency ) {
	 * 
	 * var book = repository.getById(id); if ( book == null) throw new
	 * RuntimeException("Book not Found!");
	 * 
	 * //Consumindo o cambio-service com RestTemplate HashMap<String, String> params
	 * = new HashMap<>(); params.put("amount", book.getPrice().toString());
	 * params.put("from", "USD"); params.put("to", currency);
	 * 
	 * var response = new RestTemplate()
	 * .getForEntity("http://localhost:8000/cambio-service/" +
	 * "{amount}/{from}/{to}", Cambio.class, params );
	 * 
	 * var cambio = response.getBody();
	 * 
	 * var port = environment.getProperty("local.server.port");
	 * book.setEnvironment(port); book.setPrice(cambio.getConvertedValue());
	 * 
	 * return book; // return new Book(1L, "Nigel Poulton", "Docker Deep Dive", new
	 * Date(), Double.valueOf(13.7), currency, port); //retorna um mock
	 * 
	 * }
	 */
	@GetMapping("/{id}/{currency}")
	public Book findBookById(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
		var book = repository.getById(id);
		if ( book == null) throw new RuntimeException("Book not Found!");
		
		
		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());
		book.setPrice(cambio.getConvertedValue());
		
		return book;
//		return new Book(1L, "Nigel Poulton", "Docker Deep Dive", new Date(), Double.valueOf(13.7), currency, port); //retorna um mock
		
	}
}

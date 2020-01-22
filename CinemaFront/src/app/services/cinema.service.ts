import { Injectable, OnInit } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import { ReplaySubject } from 'rxjs';
import { projection } from '@angular/core/src/render3';
@Injectable({
  providedIn: 'root'
})
export class CinemaService{
   private host :String ="http://localhost:8080";
  constructor(private http: HttpClient) { }
  
  public  getVilles(){
    return this.http.get(this.host+"/villes");
  }

  public getCinemas(v){
    return this.http.get(v._links.cinemas.href);
  }
  public getSalles(c){
    return this.http.get(c._links.salles.href);
  }
  public getProjections(salle:any){
    let url = salle._links.projectionFilms.href.replace("{?projection}","");
    return this.http.get(url+"?projection=p1");
  }
  /**
   * name
   */
  public getTicketsPlaces(p) {
    let url = p._links.tickets.href.replace("{?projection}","");
    return this.http.get(url+"?projection=projectionTickets");
  }
  /**
   * name
   */
  public payerTickets(dataForm) {
    return this.http.post(this.host+"/payerTicket",dataForm);
  }
}

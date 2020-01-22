import { Component, OnInit } from '@angular/core';
import { CinemaService } from 'src/app/services/cinema.service';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {
  public villes :any;
  public cinemas :any;
  public salles :any;
  public projections:any;
  public tickets:any;
  public currentVille:any;
  public currentCinema:any;
  public currentProjection:any;
  public selectedTickets:any;
  public selected:any;
  constructor(public cinemaService:CinemaService) { }

  ngOnInit() {
    this.getVilles();
  }

  public getVilles(){
    this.cinemaService.getVilles().subscribe(data=>{
            this.villes=data;
    },err=>{
      console.log(err);
    });
  }
  public onGetCinemas(v:any){
    this.currentVille=v;
    this.salles=undefined;
    this.cinemaService.getCinemas(v).subscribe(data=>{
      this.cinemas=data;
    },err=>{
    console.log(err);
  });
  }
  public onGetSalles(c){
    this.currentCinema=c;
    this.cinemaService.getSalles(c).subscribe(data=>{
      this.salles=data;
      this.salles._embedded.salles.forEach(salle=> {
              this.cinemaService.getProjections(salle).subscribe(data=>{
                salle.projections=data;
                console.log(data);
              })
          },err=>{
            console.log(err);
          });
    },err=>{
    console.log(err);
  }); 
  }

  /**
   * name
   */
  public onGetTicketsPlaces(p){
    this.currentProjection=p;
    this.cinemaService.getTicketsPlaces(p).subscribe(data=>{
      this.currentProjection.tickets=data;
      this.selectedTickets=[];
      console.log(data);
    },err=>{
      console.log(err);
    });
  }

  /**
   * name
   */
  public onSelectTicket(t) {
    if(!t.selected){
      t.selected=true;
      this.selectedTickets.push(t);
    }else{
      t.selected=false;
      this.selectedTickets.splice(this.selectedTickets.indexOf(t),1);
    }
    console.log(this.selectedTickets);  
  }
  /**
   * name
   */
  public getTicketClass(t) {
    let str="btn ticket ";
    if(t.reserve==true){
        str+="btn-danger";
    }else if(t.selected){
      str+="btn-warning";
    }else{
      str+="btn-success";
    }
    return str;
  }
  /**
   * name
   */
  public onPayeTickets(dataForm) {
    let tickets=[];
    this.selectedTickets.forEach(t=>{
       tickets.push(t.idTicket);
    })
    dataForm.tickets=tickets;
    this.cinemaService.payerTickets(dataForm).subscribe(data=>{
        alert("Tickets reserve avec succes");
        this.onGetTicketsPlaces(this.currentProjection);
    },err=>{
      console.log(err);
    });
    console.log(dataForm); 
  }

 }

import { Component, OnInit } from '@angular/core';
import { CarritoService } from './carrito.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})
export class CarritoComponent implements OnInit {

  constructor(public VM: CarritoService) { }

  ngOnInit(): void {
  }

}

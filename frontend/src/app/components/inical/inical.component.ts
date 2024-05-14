import { Component } from '@angular/core';

@Component({
  selector: 'app-inical',
  templateUrl: './inical.component.html',
  styleUrls: ['./inical.component.css']
})
export class InicalComponent {
  constructor() {
  localStorage.clear()

  }

}

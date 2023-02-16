import { Component, OnInit } from '@angular/core';
import {CalculateRequest} from "../model/calculate-request";
import {DataService} from "../data.service";

@Component({
  selector: 'app-calculate',
  templateUrl: './calculate.component.html',
  styleUrls: ['./calculate.component.css']
})
export class CalculateComponent implements OnInit {

  formCalculate = new CalculateRequest('');
  message = '';

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.log('Sending expression: ', this.formCalculate.expression);
    this.dataService.calculate(this.formCalculate).subscribe(
      result => {
        console.log('Received result ', result);
        this.message = 'Результат = ' + result;
      },
      error => {
        console.log('Something went wrong: ', error);
      }
    )
  }
}

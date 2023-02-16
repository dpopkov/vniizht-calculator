import { Component, OnInit } from '@angular/core';
import {CalculateRequest} from "../model/calculate-request";
import {DataService} from "../data.service";
import {HttpErrorResponse} from "@angular/common/http";

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
    this.dataService.calculate(this.formCalculate).subscribe(
      result => {
        this.message = 'Результат = ' + result;
      },
      (error: HttpErrorResponse) => {
        this.message = error.error?.message;
        console.log('Something went wrong: ', error);
      }
    )
  }
}

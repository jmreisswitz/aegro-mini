import { Component, OnInit } from '@angular/core';

import { Farm } from "../../../core/models/farm";
import { FarmService } from "../../../core/services/farm.service";

@Component({
  selector: 'app-farms',
  templateUrl: './farms.component.html',
  styleUrls: ['./farms.component.css']
})
export class FarmsComponent implements OnInit {

  farmList: Farm[] = [];

  constructor(private farmService: FarmService) { }

  ngOnInit(): void {
    this.getFarms();
  }

  private getFarms(): void {
    this.farmService.getFarms()
      .subscribe(farms => this.farmList = farms['data']);
  }
}
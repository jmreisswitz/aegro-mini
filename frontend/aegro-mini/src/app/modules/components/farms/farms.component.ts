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
  newFarmName: string = '';

  constructor(private farmService: FarmService) { }

  ngOnInit(): void {
    this.getFarms();
  }

  addFarm(name: string) {
    this.farmService.addFarm({name} as Farm)
      .subscribe(farm => this.farmList.push(farm['data']));
    this.getFarms();
  }

  getFarms() {
    this.farmService.getFarms()
      .subscribe(farms => this.farmList = farms['data']);
  }
}

import { Component, OnInit } from '@angular/core';

import { Farm } from "../../../core/models/farm";
import {FarmInMemoryService} from "../../../core/services/farm-in-memory.service";

@Component({
  selector: 'app-farms',
  templateUrl: './farms.component.html',
  styleUrls: ['./farms.component.css']
})
export class FarmsComponent implements OnInit {

  farmList: Farm[];

  constructor(private farmService: FarmInMemoryService) { }

  ngOnInit(): void {
    this.getFarms();
  }

  private getFarms(): void {
    this.farmList = this.farmService.getFarms();
  }
}

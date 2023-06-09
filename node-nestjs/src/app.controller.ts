import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { Employee } from './modules/employee/employee';

@Controller()
export class AppController {
  constructor() {}

  @Get('/json')
  getEmployees(): Employee[] {
    const user1 = new Employee('1', 'John', 'Doe');
    const user2 = new Employee('2', 'Jane', 'Doe');
    const user3 = new Employee('3', 'John', 'Smith');

    return [user1, user2, user3];
  }
}

<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class ApiController extends Controller
{
    public function healthcheck()
    {
        return response()->json('Ok');
    }

    public function getEmployees()
    {
        $employees = [
            ['id' => 1, 'firstname' => 'John', 'lastname' => 'Doe'],
            ['id' => 2, 'firstname' => 'Peter', 'lastname' => 'Parker'],
            ['id' => 3, 'firstname' => 'Tony', 'lastname' => 'Stark']
        ];

        return response()->json($employees);
    }

    public function createEmployee(Request $request)
    {
        usleep(300000); // Delay de 300ms

        return response()->json(42);
    }
}

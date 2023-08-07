from flask import Flask, request


app = Flask(__name__)

@app.route('/healthcheck')
def healthcheck():
    return "OK"

employees = [
    { 'Id': 1, 'Firstname': "John", 'Lastname': "Doe" },
    { 'Id': 2, 'Firstname': "Peter", 'Lastname': "Parker" },
    { 'Id': 3, 'Firstname': "Tony", 'Lastname': "Stark" }
]

@app.route('/employees')
def get_employees():
    return employees

@app.route('/employees', methods=['POST'])
def post_employees():
    body = request.get_json()
    employees.append(body)

    return body

if __name__ == '__main__':
    app.run(host='0.0.0.0')
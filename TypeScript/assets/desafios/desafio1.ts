// // Como podemos rodar isso em um arquivo .ts sem causar erros?

// let employee = {};
// employee.code = 10;
// employee.name = "John";

let employee: { code: number; name: string } = {
  code: 0,
  name: "",
};

employee.code = 32;
employee.name = "Paulo";

console.log(employee);

interface employeeInfo {
  code: number;
  name: string;
}

let employee2: employeeInfo = {
  code: 12,
  name: "Bianca",
};

console.log(employee2);

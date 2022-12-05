const baseUrl = "http://localhost:8080";
const exampleForm = document.getElementById("add-form");

async function getStudents() {
    const response = await fetch(`${baseUrl}/students`);
    return response.json();
}

function show(students) {
    const table = document.getElementById("students-body");
    table.innerHTML = '';

    students.forEach(student => {
        let tr = document.createElement("tr");
        let id = document.createElement("td");
        id.innerText = student.id;
        let name = document.createElement("td");
        name.innerText = student.name;
        let surname = document.createElement("td");
        surname.innerText = student.surname;
        let average = document.createElement("td");
        average.innerText = student.average;
        let del = document.createElement("button");
        del.innerText = "Delete";
        del.addEventListener("click", async () => handleDeleteStudent(student.id));
        let delOut = document.createElement("td");
        delOut.append(del);
        tr.append(id, name, surname, average, delOut);
        table.append(tr);
    });
}

async function handleDeleteStudent(id) {
    await fetch(`${baseUrl}/delete?id=${id}`, {method: "DELETE"});
    const students = await getStudents();
    show(students);
}

async function handleFormSubmit(event) {
    event.preventDefault();
    const form = event.currentTarget;
    const url = `${baseUrl}/add`;
    try {
        const formData = new FormData(form);
        await postFormDataAsJson({url, formData});
        const students = await getStudents();
        show(students);
    } catch (error) {
        console.error(error);
    }
}

async function postFormDataAsJson({url, formData}) {
    const plainFormData = Object.fromEntries(formData.entries());
    const formDataJsonString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: formDataJsonString,
    };

    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response;
}

getStudents().then(students => show(students));

exampleForm.addEventListener("submit", handleFormSubmit);
const baseUrl = "http://localhost:8080";
const form = document.getElementById("form");
const editForm = document.getElementById("edit-form");

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
        let edit = document.createElement("button");
        edit.innerText = "Edit";
        edit.addEventListener("click", async () => handleEditStudent(student));
        let editOut = document.createElement("td");
        editOut.append(edit);
        let del = document.createElement("button");
        del.innerText = "Delete";
        del.addEventListener("click", async () => handleDeleteStudent(student.id));
        let delOut = document.createElement("td");
        delOut.append(del);
        tr.append(id, name, surname, average, editOut, delOut);
        table.append(tr);
    });
}

async function handleEditStudent(student) {
    let idField = document.getElementById("id");
    let nameField = document.getElementById("name");
    let surnameField = document.getElementById("surname");
    let averageField = document.getElementById("average");
    let submitField = document.getElementById("submit");
    idField.value = student.id;
    nameField.value = student.name;
    surnameField.value = student.surname;
    averageField.value = student.average;
    submitField.value = "Edit Student";
}

async function handleDeleteStudent(id) {
    await fetch(`${baseUrl}/delete?id=${id}`, {method: "DELETE"});
    const students = await getStudents();
    show(students);
}

async function handleFormSubmit(event) {
    event.preventDefault();
    const form = event.currentTarget;
    try {
        const formData = new FormData(form);
        await postFormDataAsJson({formData, isEdit: !!formData.get("id")});
        const students = await getStudents();
        show(students);
        form.reset();
        let idField = document.getElementById("id");
        idField.value = null;
        let submitField = document.getElementById("submit");
        submitField.value = "Add new Student";
    } catch (error) {
        console.error(error);
    }
}

async function postFormDataAsJson({formData, isEdit}) {
    const plainFormData = Object.fromEntries(formData.entries());
    const formDataJsonString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: isEdit ? "PUT" : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: formDataJsonString,
    };

    let url = isEdit ? `${baseUrl}/edit` : `${baseUrl}/add`;

    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response;
}

getStudents().then(students => show(students));

form.addEventListener("submit", handleFormSubmit);
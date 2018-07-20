window.onload=initApp;
function initApp() {
    const node = {
        like: document.getElementById("itemAdd"),
        dislike: document.getElementById("itemNext"),
        joke: document.getElementById("textJoke")
    };

    const store = {
        id: null
    };

    fetch("/rest/getJoke", {
        method: "get"
    })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            throw new Error("Ошибка при первой загрузке!");
        })
        .then(data => {
            node.joke.innerHTML = data.text;
            store.id = data.id;
            // node.joke.textContent = data.text;
        })
        .catch(err =>{
            console.warn(err.message);
        })

    node.like.addEventListener("click", function () {
        console.log("Loh");
        fetch("/rest/add", {
            method: "post",
            body: store.id
        })
            .then(response => {
                if(response.ok){
                    store.id=null;
                    fetch("/rest/getJoke", {
                        method: "get"
                    })
                        .then(response => {
                            if(response.ok){
                                return response.json();
                            }
                            throw new Error("Какая-то ошибка!");
                        })
                        .then(data => {
                            node.joke.innerHTML = data.text;
                            store.id = data.id;
                        })
                        .catch(err =>{
                            console.warn(err.message);
                        })
                }
                else {
                    throw new Error("Нет, тебе это не нравится!");
                }
            })
            .catch(err =>{
                console.warn(err.message);
            })
    });

    node.dislike.addEventListener("click", function () {
        console.log("Pidr");
        fetch("/rest/getJoke", {
            method: "get"
        })
            .then(response => {
                if(response.ok){
                    return response.json();
                }
                throw new Error("Иди нафиг!");
            })
            .then(data => {
                node.joke.innerHTML = data.text;
                store.id = data.id;
                // node.joke.textContent = data.text;
            })
            .catch(err =>{
                console.warn(err.message);
            })
    });


}



const responseField = document.getElementById("responseField");
const startYearField = document.getElementById("StartYearField");
const endYearField = document.getElementById("EndYearField");

const moodSelector = document.getElementById("MoodSelector");
const genreSelector = document.getElementById("GenreSelector");

async function test() {
    //const response = await fetch("http://192.168.0.106:8080/api/test", {
    const response = await fetch("http://localhost:8080/api/test", {
        method: 'GET'
    }
    );
    try{
        if (!response.ok) throw new Error('Ошибка сервера');

        const result = await response.text();
        responseField.textContent = result;

    } catch (err){
        console.log(err);
    }
    
}

async function askRecommendation() {
    const params = new URLSearchParams({
        userMood: moodSelector.value,
        startYear: startYearField.value,
        endYear: endYearField.value,
        genre: genreSelector.value
    });

    //const response = await fetch(`http://192.168.0.104:8080/api/recommendationString?${params.toString()}`, {
    const response = await fetch(`http://localhost:8080/api/recommendationString?${params.toString()}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    try{

        if (!response.ok) throw new Error("Ошибка сервера");

        const result = await response.text();
        responseField.textContent = result;

    } catch (err){
        console.log(err);
    }
    
}
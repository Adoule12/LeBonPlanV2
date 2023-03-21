window.onload = () => {
    window.addEventListener("scroll", () => {
        let hauteur = document.documentElement.scrollHeight - window.innerHeight
        let position = window.scrollY
        let largeur = document.documentElement.clientWidth
        let barre = position / hauteur * largeur
        document.getElementById("progress").style.width = barre + "px"
    })
}

let rotate=()=>{
    let matete = document.getElementById("logoLBP");
    matete.style.transform ='rotate(360deg)';

}

let rotateback=()=>{
    let matete = document.getElementById("logoLBP");
    matete.style.transform ='rotate(0deg)';
}
let count=()=>{
    let txt_nbAd = document.getElementById("nbAd");
    let backup = document.getElementById("nbAd");
}


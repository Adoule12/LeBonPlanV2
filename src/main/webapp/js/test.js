const image_drop = document.querySelector("#image_drop");
let uploaded_image;

image_drop.addEventListener('dragover', (event) => {
    event.stopPropagation();
    event.preventDefault();
    event.dataTransfer.dropEffect = 'copy';
});

image_drop.addEventListener('drop', (event) => {
    event.stopPropagation();
    event.preventDefault();
    fileList = event.dataTransfer.files;
    document.getElementById('image_drop').files = fileList;
    document.getElementById('btImage').style.display = 'block';
    readImage(fileList[0]);
});

readImage = (file) => {
    const reader = new FileReader();
    reader.addEventListener('load', (event) => {
        uploaded_image = event.target.result;
        document.querySelector("#image_drop").style.backgroundImage = `url(${uploaded_image})`;
    });
    reader.readAsDataURL(file);
}
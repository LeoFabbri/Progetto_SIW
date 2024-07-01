function toggleArtistFields() {
    const artistRadio = document.getElementById('artist');
    const stageNameField = document.getElementById('stageNameField');
    const biographyField = document.getElementById('biographyField');
    if (artistRadio.checked) {
        stageNameField.style.display = 'block';
        biographyField.style.display = 'block';
        document.getElementById('stageName').required = true;
        document.getElementById('biography').required = true;
    } else {
        stageNameField.style.display = 'none';
        biographyField.style.display = 'none';
        document.getElementById('stageName').required = false;
        document.getElementById('biography').required = false;
    }
}

document.addEventListener('DOMContentLoaded', toggleArtistFields);

document.querySelectorAll('.btn-check').forEach(radio => {
    radio.addEventListener('change', function() {
        document.querySelectorAll('.btn').forEach(btn => {
            btn.classList.remove('active');
        });
        document.querySelector(`label[for="${this.id}"]`).classList.add('active');
    });
});
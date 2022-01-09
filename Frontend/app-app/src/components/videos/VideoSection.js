import React from 'react';
import { Button } from '../buttons/Button';
import './VideoSection.css';
import '../../App.css';

function VideoSection() {
    return (
        <div>
            <video src="videos/video.mp4" autoPlay loop muted></video>
            <div id='textOverlay'>
                <h1 id='videoH1'>ThymeCrunch</h1>
                <p id='videoP'>No thyme for complicated recipies? No Worries, We'll make it easy!</p>
                <div className='video-btns'>
                    <Button 
                        className='btns' buttonStyle='btn--outline' 
                        buttonSize='btn--large'>
                        Thyme to Cook!
                    </Button>
                    <Button 
                        className='btns' buttonStyle='btn--primary' 
                        buttonSize='btn--large'>
                        Watch a video! <i className='far fa-play-circle'/>
                    </Button>
                </div>
            </div>
        </div>
    )
}

export default VideoSection

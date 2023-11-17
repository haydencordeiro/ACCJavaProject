import { useState } from 'react'

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Button, CardActionArea, CardActions } from '@mui/material';



function CustomCard({productThumbnail, productName, productSellingPrice, productComparisonDetails, onButtonClick, productClickCount, dateScraped}){
    return (<Card  style={{ width: '33%' }}>
      <CardContent>
        <img src={productThumbnail} alt="Product Thumbnail" style={{ maxWidth: '100%' }} />
        <Typography variant="h5" component="div">
          {productName}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Selling Price: {productSellingPrice}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Comparison Details: {productComparisonDetails}
        </Typography>
        <Typography variant="body3" color="text.secondary">
          Comparison Details: {dateScraped}
        </Typography>
        <Button variant="contained" fullWidth onClick={() => onButtonClick(productName)}>
          View :{productClickCount}
        </Button>
      </CardContent>
    </Card>);
}


export default CustomCard;
